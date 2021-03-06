(ns app.auth.views.log-in
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [app.components.form-container :refer [form-container]]
            [app.components.form-group :refer [form-group]]
            ["@chakra-ui/react" :refer [Button Link Stack]]))

(defn log-in
  []
  (let [initial-values {:email    ""
                        :password ""}
        values         (r/atom initial-values)
        error          (rf/subscribe [:errors/log-in])
        loading?       (rf/subscribe [:loading/log-in])]
    (fn []
      [form-container
       "Sign In"
       #(rf/dispatch [:log-in @values])
       [:> Stack {:spacing 6}
        [form-group
         {:label  "Email Address"
          :id     :email
          :type   "email"
          :error  (when @error "Invalid email/password")
          :values values}]
        [form-group
         {:label  "Password"
          :id     :password
          :type   "password"
          :error  (when @error "Invalid email/password")
          :values values}]
        [:> Button {:type         "submit"
                    :color-scheme "blue"
                    :size         "lg"
                    :font-size    "md"
                    :is-loading   @loading?}
         "Sign In"]
        [:> Link {:font-size "sm"
                  :href      "/sign-up"}
         "Don't have an account? Sign Up!"]]])))
