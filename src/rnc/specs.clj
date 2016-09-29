(ns rnc.specs
  (:require [clojure.spec :as s]
            [clojure.string :as str]
            [rnc.core :as core]
            [clojure.test.check.generators :as gen]))

(def valid-chars #{\I \V \X \L \C \D \M})

(s/def ::roman-numeral (s/with-gen (s/and string?
                                          (complement empty?)
                                          (partial every? valid-chars)
                                          #(->> (partition-by identity %)
                                                (remove (partial every? (partial = \M)))
                                                (map count)
                                                (every? (partial > 4))))
                         #(gen/fmap (partial str/join "") (gen/vector (gen/elements valid-chars) 1 5))))

(s/def ::pos-int (s/and int?
                        #(< 0 % 5000)))

(s/fdef core/roman-to-decimal
        :args (s/cat :input ::roman-numeral)
        :ret ::pos-int)

(s/fdef core/decimal-to-roman
        :args (s/cat :input ::pos-int)
        :ret ::roman-numeral)

(s/fdef core/add
        :args (s/+ ::roman-numeral)
        :ret (s/+ ::roman-numeral))
