import { lazy, StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { BrowserRouter, Outlet, Routes, Route } from 'react-router-dom';

import { Provider } from 'react-redux';
import store from './Store/store';

import { GOOGLE_CLIENT_ID } from './Utilities/const';
import './main.css';
import { GoogleOAuthProvider } from '@react-oauth/google';

import { QueryClientProvider } from '@tanstack/react-query';
import { queryClient } from './Utilities/fetchSettings';

import { setUpInterceptors } from './Utilities/fetchSettings';
setUpInterceptors(store);


const LogInPage = lazy(() => import('./LoginPage'));
const UserActivityPage = lazy(() => import('./Activity/UsersActivityPage'));
const ClockPage = lazy(() => import('./Clock/ClockComponent'));
const TopBar =  lazy(() => import('./TopBar/TopBarPage'));

function MainPage(){

  return (
    <div>
      <TopBar />
      <Outlet />
    </div>
  )
}


function App(){

  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<MainPage/>}>
            <Route path='login' element={<LogInPage/>}/>
            <Route path='main' element={
            <div>
                <UserActivityPage />
                <ClockPage />
            </div>}/>
          </Route>
        </Routes>
      </BrowserRouter>
    </div>
  )
}

createRoot(document.getElementById('root')!).render(
  <GoogleOAuthProvider clientId={GOOGLE_CLIENT_ID}>
    <QueryClientProvider client={queryClient}>
      <StrictMode>
        <Provider store={store}>
          <App />
        </Provider>
      </StrictMode>
    </QueryClientProvider>
  </GoogleOAuthProvider>
)