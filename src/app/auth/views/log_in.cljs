(ns app.auth.views.log-in
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [app.components.page-nav :refer [page-nav]]
            [app.components.form-group :refer [form-group]]
            ["@material-ui/core" :as mui]))

(defn log-in
  []
  (let [initial-values {:email "" :password ""}
        values (r/atom initial-values)]
    (fn []
      [:<>
       [page-nav {:center "Log In"}]
       [:form
        [:> mui/Container { :maxWidth "xs" }
         [form-group {:label "Email Address"
                      :id :email
                      :type "email"
                      :values values}]
         [form-group {:label "Password"
                      :type "password"
                      :id :password
                      :values values}]
         [:> mui/Button {:variant "contained"
                         :color "primary"
                         :type "submit"
                         :fullWidth true
                         :style {:marginTop 16
                                 :marginBottom 16}
                         :on-click #(rf/dispatch [:log-in @values])}
          "Log In"]
         [:> mui/Link {:href "#sign-up"
                       :variant "body2"
                       :color "inherit"
                       :on-click #(rf/dispatch [:set-active-nav :sign-up])}
          "Don't have an account? Sign Up!"]]]])))
