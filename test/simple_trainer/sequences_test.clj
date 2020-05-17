(ns simple-trainer.sequences-test
  (:require [clojure.test :refer :all]
            [simple-trainer.sequences :as s]))

(deftest test-sequence
  (is (= (list 1 3 5 7 9) (take 5 (s/seq-to-guess + 2 1))))
  (is (= (list 1 2 4 8 16) (take 5 (s/seq-to-guess * 2 1)))))  
