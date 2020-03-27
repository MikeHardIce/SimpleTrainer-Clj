(ns simple-trainer.core
  (:require [simple-trainer.guessing :as guess])
  ;;(:require [simple-trainer.memorize-words :as memo])
  (:require [seesaw.core :as seesaw])
  (:gen-class))

(def title (seesaw/label :text "Menu"))

(def main (seesaw/text :editable? true :multi-line? true :text ""))

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
(defn display-options []
  (seesaw/config! main :text "Here are your options:
                                  1 - Guessing Numbers
                                  2 - Memorizing Words
                                  0 - Quit"))

(defn out [& args]
  (println (str "Write to main: " (apply str args)))
  (seesaw/config! main :text (apply str args)))

(defn read-hook 
  ([fn-continue-with] (read-hook fn-continue-with {}))
  ([fn-continue-with params] 
   (seesaw/listen enter :action (fn [ctrl]
                                  (let [value (seesaw/config input :text)
                                       sanitized (if (empty? value) 0 value)]
                                    (do 
                                      (println (str "Inside Read-hook: " value)))
                                      (fn-continue-with sanitized params))))))

(defn handle-selection [selection {:keys [continue-with]}]
  (let [choice (Integer/parseInt selection)]
    (cond
      (= choice 1) (do 
                      (println "Inside handle-selection")
                      (guess/start read-hook out 100 continue-with))
        ;;(= selection 2) [(memo/start read-hook out})
        ;;                 (continue-with)]))
        :else (System/exit 0))))
                
  
    

(defn menu []
  (display-options)
  (println "Inside menu")
  (read-hook handle-selection {:continue-with menu}))

(defn -main 
  "Starts the simple trainer"
  [& args]
  ;;(seesaw/show! window)
  (seesaw/invoke-later 
    (-> window
      seesaw/show!))
    (menu))