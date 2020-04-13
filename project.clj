(defproject simple-trainer "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [seesaw "1.5.0"]]
  :main ^:skip-aot simple-trainer.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all} })
