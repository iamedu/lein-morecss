(ns leiningen.morecss
  (:require [clojure-watch.core :refer [start-watch]])
  (:import [com.asual.lesscss LessEngine]
           [java.io File]))

(defn- compile-key [{:keys [less-file css-file]}]
  (println "Compiling from" less-file "to" css-file)
  (let [engine (LessEngine.)
        compiled-text (->> (File. less-file)
                           (.compile engine))]
    (spit css-file compiled-text)
    (println css-file "compilation successful")))

(defn- once [morecss ks]
  (doseq [k ks]
    (compile-key (get morecss k))))

(defn- auto-single-config [{:keys [directories] :as data}]
  (for [d directories]
    {:path d
     :event-types [:modify :delete]
     :bootstrap (fn [path] (println "Starting to watch" path))
     :callback (fn [event filename] (compile-key data))
     :options {:recursive true}}))

(defn- auto-full-config [morecss ks]
  (flatten (for [k ks] (auto-single-config (get morecss k)))))

(defn auto [morecss ks]
  (start-watch (auto-full-config morecss ks)))

(defn morecss
  "Compiles less files into css
  You can add several configurations as a map under the morecss key
  Each compilation must have:

  :less-file
  :css-file is the file to generate
  :directories a list of the folders to watch for each compilation

  Here is a complete example:

  :morecss {:bootstrap {:less-file \"path/to/watch/dir1/bootstrap.less\"
  :css-file \"path/to/compiled/file.css\"
  :directories [\"path/to/watch/dir1\" \"path/to/watch/dir2\"]}}

  You can run it like this:

  lein morecss once
  lein morecss auto

  Or if you want to only generate one file:

  lein morecss once bootstrap
  lein morecss auto bootstrap"
  [{:keys [morecss]} command & ks]
  (let [ks (cond
             (nil? ks) (keys morecss)
             :else (map keyword ks))]
    (case command
      "once" (once morecss ks)
      "auto" (auto morecss ks))))
