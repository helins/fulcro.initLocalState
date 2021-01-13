(ns app.root

  ""

  {:author "Adam Helinski"}

  (:require [com.fulcrologic.fulcro.application :as    F.app]
            [com.fulcrologic.fulcro.components  :as    F.comp
                                                :refer [defsc]]
            [com.fulcrologic.fulcro.dom         :as    F.dom]
            [com.fulcrologic.fulcro.mutations   :refer [defmutation]]))


(declare mount)


;;;;;;;;;;


(defmutation add-child

  [_params]

  (action [env]
    (swap! (env :state)
           update
           :root/children
           conj
           {:child/id (rand)})))


;;;;;;;;;;


(defsc Child [_this {:child/keys [id]}]

  {:ident          :child/id
   :initLocalState (fn [_this _props]
                     (println :init-child)
                     {})
   :query          [:child/id]}

  (F.dom/div {}
             (str "Child "
                  id)))


(def ui-child

  (F.comp/factory Child
                  {:keyfn :child/id}))



(defsc Root [this {:root/keys [children]}]

  {:initial-state (fn [_]
                    {:root/children []})
   :query         [{[:root/children '_] (F.comp/get-query Child)}]}


  (F.dom/div {}
             (F.dom/button {:onClick #(F.comp/transact! this
                                                        [(add-child)])}
                           "ADD CHILD")
             (map ui-child
                  children)))


;;;;;;;;;;


(defonce instance

  (F.app/fulcro-app))


;;;;;;;;;;


(defn ^:export init

  ""

  []

  (mount))



(defn ^:export mount

  ""

  []

  (F.app/mount! instance
                Root
                "root"))
