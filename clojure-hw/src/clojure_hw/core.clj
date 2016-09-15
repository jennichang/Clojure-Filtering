(ns clojure_hw.core
  (:require [clojure.string :as str] [clojure.data.json :as json])
  (:gen-class))


(defn -main []
  (println "Type your category:")
  (let [people (slurp "people.csv") ;reads the csv file into a string.
        people (str/split-lines people) ;rebinding people to itself, but split into different lines
        people (map (fn [line] ;rebind people, value is mapping. function baseing off of map is run split method
                      (str/split line #",")) ;split current line on the comma. run map and pass people into map
                    people)
        header (first people) ;create new local variable "header", takes first item from people
        people (rest people) ; redefines people to contain everything after first value
        people (map (fn [line]
                      (zipmap header line)) ;using strings in header as keys. will mix together line and header
                    people)
        category-name (read-line) ;like scanner next line
        people (filter (fn [line] ;filter takes in an argument.
        ; for each person, get the categor element from line map, see if that equals what was inputted from read-line
                         (= (get line "category") category-name))
                       people)
        ;file-text (pr-str people)]
        file-text (json/write-str people)] ;bind file-text parameter to people.converts map to json
    ;(spit "filtered_people.edn" file-text)))
    (spit "filtered_purchases.json" file-text))) ;spits back into a file.
