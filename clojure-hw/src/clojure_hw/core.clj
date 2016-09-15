(ns clojure_hw.core
  (:require [clojure.string :as str] [clojure.data.json :as json])
  (:gen-class))


(defn -main []
  (println "Type your category:")
  (let [purchase (slurp "purchases.csv") ;reads the csv file into a string.
        purchase (str/split-lines purchase) ;rebinding people to itself, but split into different lines
        purchase (map (fn [line] ;rebind people, value is mapping. function baseing off of map is run split method
                      (str/split line #",")) ;split current line on the comma. run map and pass people into map
                    purchase)
        header (first purchase) ;create new local variable "header", takes first item from people
        purchase (rest purchase) ; redefines people to contain everything after first value
        purchase (map (fn [line]
                      (zipmap header line)) ;using strings in header as keys. will mix together line and header
                      purchase)
        category-name (read-line) ;like scanner next line
        purchase (filter (fn [line] ;filter takes in an argument.
        ; for each person, get the categor element from line map, see if that equals what was inputted from read-line
                         (= (get line "category") category-name))
                         purchase)
        ;file-text (pr-str people)]
        file-text (json/write-str purchase)] ;bind file-text parameter to people.converts map to json
    ;(spit "filtered_people.edn" file-text)))
    (spit "filtered_purchases.json" file-text))) ;spits back into a file.
