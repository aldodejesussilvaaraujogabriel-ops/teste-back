import React from "react";
// 1. Importa os componentes apontando para as pastas corretas
import NavBar from "./components/NavBar";
import Home from "./pages/home";

// 2. Importa o seu SCSS que está dentro da pasta style
import "./style/custom.scss";

function App() {
  return (
    <div>
      {/* Renderiza a NavBar no topo */}
      <NavBar />
      
      {/* Renderiza o conteúdo da página logo abaixo */}
      <main>
        <Home />
      </main>
    </div>
  );
}

export default App;