(ns app.auth.events
  (:require [re-frame.core :refer [reg-event-db reg-event-fx]]))

(reg-event-fx
 :log-in
 (fn [_ [_ credentials]]
   {:firebase/sign-in-with-email-and-password credentials
    :dispatch                                 [:navigate :app.router/campaigns]}))

(reg-event-fx
 :log-out
 (fn [_ _]
   {:firebase/sign-out nil
    :dispatch          [:navigate :app.router/frontpage]}))

(reg-event-fx
 :sign-up
 (fn [_ [_ credentials]]
   {:firebase/create-user-with-email-and-password credentials}))

(reg-event-db
 :set-current-user
 (fn [db [_ user]]
   (-> db
       (assoc :auth user)
       (update-in [:errors] dissoc :log-in :sign-up))))

(reg-event-db
 :remove-current-user
 (fn [db _]
   (dissoc db :auth)))

(reg-event-db
 :sign-up-failure
 (fn [db [_ error]]
   (assoc-in db [:errors :sign-up] error)))

(reg-event-db
 :log-in-failure
 (fn [db [_ error]]
   (assoc-in db [:errors :log-in] error)))