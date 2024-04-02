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
;; open parens, operator, operands, close parens -> every clojure operation
;; a fn call is just a fn expression where the operator is a fn

(or + -) ;; will return + as its the first truthy or last falsey
((or + -) 1 1 1)

((and (= 1 1) +) 1 2 3) ;; and is first falsey or last truthy

((first [+ 0]) 1 2 3)

;; functions accept any other expressions as args
;; fns that accept fns OR return an fn -> HOF (higher order fn)
;;; PL with HOF support -> supports "first class fns" aka fns as values

(inc 1)
(map inc [1 2 3 4]) ;; quick note - map doesnt return a vec


;; remember clojure evals recursively (DFS) before passing to an fn
;; all submforms are evaluated
;; (+ (inc 199) (/ 100 (- 7 2)))
;; (+ 200 (/ 100 (- 7 2))) ; evaluated "(inc 199)"
;; (+ 200 (/ 100 5)) ; evaluated (- 7 2)
;; (+ 200 20) ; evaluated (/ 100 5)
;; 220 ; final evaluation

(comment "
expression types
  1. fn expressions -> fn is the operator
  2. macro calls
  3. special forms e.g. if expressions
          
  a main feature of special forms they dont ALWAYS eval all their operands.
  ")

(defn my-incr
  "increments the number passed in"
  [num]
  (+ num 1))

(my-incr 2)

(map my-incr [1 2 3])

;; the number of parameters a function accepts is it's 'arity'
(defn zero-arity
  []
  "you passed no args my dude")

(defn one-arity
  [one]
  (one))

(defn two-arity
  [one two]
  (str one two))

;; arity overloading -> defiine function to run multiple bodies
;; based on num args passed

(defn multi-arity
  ([one two] (two-arity one two))
  ([one] (one-arity one))
  ([] (zero-arity)))

(multi-arity)
(multi-arity "1")
(multi-arity "1" "2")

;; arity overloading lets us provide default args

(defn arity-overloader
  ([one two] (str one two))
  ([one] (arity-overloader one "DEFAULT")))

(arity-overloader "1" "2")
(arity-overloader "---")

;; you can leverage arity to different things entirely
;; since you can run any kind of body
(defn do-diff-stuff-arity
  ([] "yoo return this str")
  ([num] (inc num)))

(do-diff-stuff-arity)
(do-diff-stuff-arity 1)

;; variable arity fns

(defn add-one
  [num]
  (inc num))

(defn add-one-to-all
  [& args] ;; rest parameter -> put the rest of these args into this var
  (map add-one args))

(add-one 1)
(add-one-to-all 1 1 5 5 5)


(defn favs
  [name & things]
  (str "my name is: "
       name
       " and these are my fav things: "
       (clojure.string/join ", " things)))

(favs "josh" "coffee" "chocolate" "lisp")

;; destructuring

;; destructure list

(defn destructure-stuff
  [[elt1 elt2 & rest-of-the-elts]]
  (str elt1 elt2 (clojure.string/join "" rest-of-the-elts)))

(destructure-stuff ["hello" "world" "blah1" "blah2" "blah3"])

;; destructure maps

(defn destructure-stuff-map
  [{a :a b :b}]
  (str a b))
(destructure-stuff-map {:a "hello" :b "world"})

(defn destructure-by-key

  [{:keys [a b] :as whole-map}]
  (str a b)
  (str "use whole map for something: " (:a whole-map)))

(destructure-by-key {:a "hey" :b "you"})


;; checkpoint function body
