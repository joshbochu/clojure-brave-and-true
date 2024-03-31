(ns clojure-brave-and-true.do-things)

(+ 1 2 3)
(str "con" "cat" "enate")

(if true
  (str "it's true!")
  (str "wont reach.."))

(if false
  (str "wont reach")
  (str "it's false!"))

(if false
  (str "skips to return nil"))

;; eval multiple exprs in order
;;  returns the last eval
(if true
  (do (println "Success!")
      "By Zeus's hammer!")
  (do (println "Failure!")
      "By Aquaman's trident!"))

(when true
  (println "print something")
  "returns this str after evaling stuff")

(if "truthy"
  "truthy output"
  "wont reach")

(if nil
  "wont reach"
  "falsey output")

(= 1 1)
(= nil nil)
(= nil 1)

;; checkpoint and operators




