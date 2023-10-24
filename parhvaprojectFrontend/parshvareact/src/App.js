import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Home from './component/Home';
import Display from './component/Display';
function App() {
  return (
    <BrowserRouter>
    <Routes>
      <Route exact path="/" element={<Home/>} />
      <Route path="/display" element={<Display/>} />
    </Routes>
  </BrowserRouter>
  );
}

export default App;
