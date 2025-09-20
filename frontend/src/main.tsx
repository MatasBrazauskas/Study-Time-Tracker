import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'

import 'main.css';

function App()
{
  return 
    (<div>
      <div>Idk THO</div>
    </div>
    );
}

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <App />
  </StrictMode>,
)
