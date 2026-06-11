import React from "react";

function Home() {
  return (
    <div>
      <section className="contact-section">
        <h2>Entre em Contato</h2>
        <p className="subtitle">Ficaremos felizes em ouvir você! Escolha um dos canais abaixo.</p>

        <div className="contact-container">
          <div className="contact-info">
            <div className="info-item">
              <div>
                <h4>Instagram</h4>
                <p>@guardias.aguas</p>
              </div>
            </div>

            <div className="info-item">
              <div>
                <h4>YouTube</h4>
                <p>Leatox</p>
              </div>
            </div>

            <form className="contact-form" id="formContato" action="/contato/enviar" method="POST">
              <div className="form-group">
                <label htmlFor="nome">Seu nome</label>
                <input type="text" id="nome" name="nome" placeholder="Digite seu nome" required />
              </div>

              <div className="form-group">
                <label htmlFor="email">Seu e-mail</label>
                <input type="email" id="email" name="email" placeholder="Digite seu e-mail" required />
              </div>

              <div className="form-group">
                <label htmlFor="mensagem">Mensagem</label>
                <textarea id="mensagem" name="mensagem" placeholder="Escreva sua mensagem..." rows="5" required></textarea>
              </div>

              <div className="form-group">
                <label>Avaliação (0 a 10)</label>
                <div className="rating">
                  <input type="radio" name="avaliacao" value="0" id="r0" /><label htmlFor="r0">0</label>
                  <input type="radio" name="avaliacao" value="1" id="r1" /><label htmlFor="r1">1</label>
                  <input type="radio" name="avaliacao" value="2" id="r2" /><label htmlFor="r2">2</label>
                  <input type="radio" name="avaliacao" value="3" id="r3" /><label htmlFor="r3">3</label>
                  <input type="radio" name="avaliacao" value="4" id="r4" /><label htmlFor="r4">4</label>
                  <input type="radio" name="avaliacao" value="5" id="r5" /><label htmlFor="r5">5</label>
                  <input type="radio" name="avaliacao" value="6" id="r6" /><label htmlFor="r6">6</label>
                  <input type="radio" name="avaliacao" value="7" id="r7" /><label htmlFor="r7">7</label>
                  <input type="radio" name="avaliacao" value="8" id="r8" /><label htmlFor="r8">8</label>
                  <input type="radio" name="avaliacao" value="9" id="r9" /><label htmlFor="r9">9</label>
                  <input type="radio" name="avaliacao" value="10" id="r10" /><label htmlFor="r10">10</label>
                </div>
              </div>

              <button type="submit">Enviar mensagem</button>
            </form>
          </div>
        </div>
      </section>

      <section id="contatos">
        <h2>Orgãos Ambientais</h2>
        {/* Dica: Se "orgaoambiental.html" for uma rota do seu app React, o ideal depois é trocar essa tag <a> por <Link to="..."> do react-router-dom */}
        <a id="link_D" href="orgaoambiental.html">saiba mais</a>
      </section>
    </div>
  );
}

export default Home;