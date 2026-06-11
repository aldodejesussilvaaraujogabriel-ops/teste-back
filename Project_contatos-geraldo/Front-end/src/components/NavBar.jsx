import React from "react";

function NavBar() {
  return (
    <header>
      <div className="brand-container">
       
        
        <h2>Guardiãs das Águas</h2>
      </div>

      <nav>
      
        <ul className="nav-links">
          <li><a href="#inicio">Início</a></li>
          <li><a href="#noticias">Notícias</a></li>
          <li><a href="#projetos">Projetos</a></li>
          <li><a href="#eventos">Eventos</a></li>
          <li>
            <details>
              <summary id="Nucleos">Nossos Núcleos</summary>
              <div className="dropdown-content" style={{ position: "absolute", background: "#B48FE9", padding: "10px", borderRadius: "8px", display: "flex", flexDirection: "column", gap: "5px", zIndex: 10 }}>
                <a href="#mg">MG</a>
                <a href="#ce">CE</a>
              </div>
            </details>
          </li>
          <li><a href="#contatos">Contato</a></li>
        </ul>
      </nav>
    </header>
  );
}

export default NavBar;