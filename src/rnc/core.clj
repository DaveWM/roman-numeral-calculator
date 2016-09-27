(ns rnc.core
  (:require [clojure.set :refer [map-invert]]))

(def numeral-map {"I" 1
                  "IV" 4
                  "V" 5
                  "IX" 9
                  "X" 10
                  "XL" 40
                  "L" 50
                  "XC" 90
                  "C" 100
                  "CD" 400 
                  "D" 500
                  "XM" 900
                  "M" 1000})

(def decimal-map (map-invert numeral-map))

(defn roman-to-decimal [roman-string]
  (->> (map #(get value-map (str %)) roman-string)
       (partition-all 2 1)
       (map (fn [[x y]]
              (if (>= x (or y 0))
                x
                (* -1 x))))
       (reduce + 0)))

(defn decimal-to-roman [decimal]
  (loop [result ""
         x decimal]
    (if (zero? x)
      result
      (let [[character-val character] (->> decimal-map
                                           sort
                                           (take-while (fn [[k v]] (<= k x)))
                                           last)
            times (quot x character-val)] 
        (recur (apply str result (repeat times character))
               (- x (* times character-val)))))))

(defn add [& nums]
  (->> nums
       (map roman-to-decimal)
       (reduce + 0)
       decimal-to-roman))
