(defproject lein-morecss "0.1.0-SNAPSHOT"
  :description "Compile several less files into a single lesscss"
  :url "https://github.com/iamedu/lein-morecss"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[com.asual.lesscss/lesscss-engine "1.4.2"]
                 [clojure-watch "0.1.9"]]
  :eval-in-leiningen true)
