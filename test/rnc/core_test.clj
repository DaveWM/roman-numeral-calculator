(ns rnc.core-test
  (:require [clojure.test :refer :all]
            [rnc.core :refer :all]))

(deftest test-roman-to-decimal
  (is (= (roman-to-decimal "I") 1))
  (is (= (roman-to-decimal "II") 2))
  (is (= (roman-to-decimal "IV") 4))
  (is (= (roman-to-decimal "XCIX") 99))
  (is (= (roman-to-decimal "DCCCXC") 890)))

(deftest test-decimal-to-roman
  (is (= (decimal-to-roman 1) "I"))
  (is (= (decimal-to-roman 3) "III"))
  (is (= (decimal-to-roman 4) "IV"))
  (is (= (decimal-to-roman 99) "XCIX"))
  (is (= (decimal-to-roman 890) "DCCCXC")))

(deftest test-add
  (is (= (add "IV" "III") "VII")))
