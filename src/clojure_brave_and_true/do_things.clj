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

;; truthy/falsey refers to how an expression is treated

;; checkpoint and operators

;; OR -> returns first truthy value or last value
;; AND -> returns first falsey value or last value

(or false nil "return this")
(and true "yo" (if true nil))
(and true "yo" "return this")

(def some-name ["a" "b" "c"])
some-name

;; data structures
;; maps
{:name {:first "john"}}
(get {:name {:first "john"}} :name)
;; equivalent to above
({:name {:first "john"}} :name)
(:name {:name {:first "john"}}) ;; chatgpt says this is more idiomatic? idk

(get {:name {:first "john"}} :does-not-exist)
(get-in {:name {:first "john"}} [:name :first])

(:does-not-exist {:a 1 :b 2}) ;; trys to lookup dne key
(:does-not-exist {:a 1 :b 2} "default") ;; same thing but default value "default"

;; vectors
(get [0 1 2] 0)
(get [0 {:name "josh" :age 100} 2] 1)
(get (vector [0 1 2]) 0) ;; create vector with vector fn
(conj [0 1 2] 3) ;; adds 3 to the end

;; lists ; generally "less performant" than vectors
;; bc vectors allow lookups O(1) vs list O(n)
`(1 2 3 4)
(nth `(:a :b :c) 0)
(nth `(:a :b :c) 2)
(conj `(1 2 3) 0) ;; conj does an append to the front!

;; rule of thumb: lists for easy add to front / macros

;; sets
#{1 "2" {:three 3}}
(hash-set 1 2 3 4 5)


(conj #{:a :b} :b)

(set [1 2 3])
(set `(1 2 3))

(contains? #{:a :b} :a)
(contains? #{:a :b} :c)

(:a #{:a :b}) ;; returns keyword
(get #{:a :b} :a)
(get #{:a :b} :dne)

;; checkpoint functions













