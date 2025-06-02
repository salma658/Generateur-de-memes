
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import MemeGenerator from './components/MemeGenerator';
import Favorites from './pages/Favorites';

function App() {
  

 return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/generate" element={<MemeGenerator />} />
        <Route path="/generate/:memeUrl" element={<MemeGenerator />} />
        <Route path="/favorites" element={<Favorites />} />
      </Routes>
    </Router>
  );
}

export default App;
