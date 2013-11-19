# morecss

Compiles less files into css, you need to specify which files to compile, the idea is to be able to compile bootstrap from less.

(And other projects that consist of many less files).

If you need to compile all of your less files in a directory, use lesscss it is an excelent plugin!

## Usage

Put `[morecss "0.1.0-SNAPSHOT"]` into the `:plugins` vector of your
`:user` profile, or if you are on Leiningen 1.x do `lein plugin install
morecss 0.1.0-SNAPSHOT`.

    $ lein morecss 'file'

## License

Copyright © 2013 Eduardo Díaz

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
