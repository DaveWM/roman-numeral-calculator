(ns rnc.core-test
  (:require [clojure.test :refer :all]
            [rnc.core :refer :all]
            [rnc.specs :as specs]
            [clojure.spec.test :as stest]
            [clojure.spec :as s]))

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

(deftest test-check
  (is (get-in (first (stest/check `decimal-to-roman)) [:clojure.spec.test.check/ret :result]))
  (is (get-in (first (stest/check `roman-to-decimal)) [:clojure.spec.test.check/ret :result])))
