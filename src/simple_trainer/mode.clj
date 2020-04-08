(ns simple-trainer.mode)

(def active-mode (atom :menu))

(defn switch-mode [mode]
  (swap! active-mode (fn [x] mode)))

(defn dispatch [out input]
    @active-mode)

(defmulti handle-input dispatch)
