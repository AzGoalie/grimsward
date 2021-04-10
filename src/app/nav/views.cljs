(ns app.nav.views
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            ["@chakra-ui/react" :refer [Avatar Box Button Flex Heading HStack Menu MenuButton MenuItem MenuList Spacer]]))

(defn nav-button
  [label href]
  [:> Button {:as          "a"
              :font-size   "sm"
              :font-weight "600"
              :href        href}
   label])

(defn nav-link
  [label href]
  [:> Button {:as          "a"
              :variant     "link"
              :font-size   "sm"
              :font-weight 400
              :href        href}
   label])

(defn nav-public
  []
  [:> HStack {:spacing 4}
   [nav-button "Sign Up" "/sign-up"]
   [nav-button "Sign In" "/sign-in"]])

(defn nav-authenticated
  []
  [:> HStack {:spacing 4}
   [nav-link "Campaigns" "/campaigns"]
   [:> Menu
    [:> MenuButton {:as      Button
                    :rounded "full"
                    :variant "link"}
     [:> Avatar {:size "sm"}]]
    [:> MenuList
     [:> MenuItem {:on-click #(rf/dispatch [:navigate :app.router/profile])}
      "Profile"]
     [:> MenuItem {:on-click #(rf/dispatch [:log-out])}
      "Sign Out"]]]])

(defn nav
  []
  [:> Box {:bg "gray.900"
           :px 4
           :mb 4}
   [:> Flex {:h               16
             :align           "center"
             :justify-content "space-between"}
    [:> Heading {:size "md"}
     "Grimsward"]
    [:> Spacer]
    (if @(rf/subscribe [:logged-in?])
      [nav-authenticated]
      [nav-public])]])

