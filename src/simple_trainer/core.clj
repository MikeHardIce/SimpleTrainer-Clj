(ns simple-trainer.core
  (:require [simple-trainer.mode :as m])
  (:require [simple-trainer.guessing :as guess])
  (:require [simple-trainer.memorize-words :as memo])
  (:require [seesaw.core :as seesaw])
  (:gen-class))

(def title (seesaw/label :text "Menu"))

(def main (seesaw/text :editable? false :multi-line? true :text ""))

(def input (seesaw/text ""))

(def enter (seesaw/button :text "Enter"))

(def panel (seesaw/border-panel :north title
            :center main
            :south (seesaw/horizontal-panel :items [input enter])
            :vgap 5 :hgap 5 :border 10))

(def window (seesaw/frame :title "Simple Trainer"
                          :content panel
                          :width 650
                          :height 550
                          :on-close :exit))

(defn out [& args]
  (let [tmp (apply str args)
       output (if (> (count tmp) 0) tmp "Here are your options:
                                          1 - Guessing Numbers
                                          2 - Memorizing Words
                                          0 - Quit")]
      (seesaw/config! main :text output))
      (seesaw/request-focus! input))

(defn display-options []
  (out))

(defmethod m/handle-input :menu [out input]
  (let [choice (Integer/parseInt input)]
    (cond
      (= choice 1) (guess/start out 100)
      (= choice 2) (memo/start out)
      :else (System/exit 0))))

(defn menu []
  (m/switch-mode :menu)
  (display-options))

(defn handle-click [e]
  (let [value (seesaw/config input :text)
      sanitized (if (empty? value) 0 value)]
      (m/handle-input out sanitized)))

(defn -main 
  "Starts the simple trainer"
  [& args]
  (seesaw/invoke-later 
    (-> window
      seesaw/show!))  
  (menu)
  (seesaw/listen enter :action handle-click)
  (seesaw/listen input :key-typed (fn [e]
                                      (when (= \newline (.getKeyChar e)) (handle-click e)))))
