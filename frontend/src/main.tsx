import { lazy, StrictMode } from 'react'
import { createRoot } from 'react-dom/client'

import { Provider } from 'react-redux';
import store from './Store/store';

const LogIn = lazy(() => import('./Login'));
import { GOOGLE_CLIENT_ID } from './const';
import './main.css';
import { GoogleOAuthProvider } from '@react-oauth/google';

function App(){
  return (
    <div>
      <LogIn />
    </div>
  )
}

createRoot(document.getElementById('root')!).render(
  <GoogleOAuthProvider clientId={GOOGLE_CLIENT_ID}>
    <StrictMode>
      <Provider store={store}>
        <App />
      </Provider>
    </StrictMode>
  </GoogleOAuthProvider>
)
