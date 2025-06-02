import React, { useEffect, useState } from "react";
import "./Home.css";

const Home = () => {
  const [memes, setMemes] = useState([]);

  // Charger tous les mèmes
  const fetchMemes = () => {
    fetch("http://localhost:8080/api/memes")
      .then((res) => res.json())
      .then((data) => setMemes(data))
      .catch((err) => console.error("Erreur:", err));
  };

  useEffect(() => {
    fetchMemes();
  }, []);

  const handleDelete = async (id) => {
    if (!window.confirm("Confirmer la suppression de ce mème ?")) return;

    try {
      const res = await fetch(`http://localhost:8080/api/memes/${id}`, {
        method: "DELETE",
      });

      if (!res.ok) throw new Error("Erreur lors de la suppression");
      alert("Mème supprimé !");
      fetchMemes(); // Rafraîchir la liste après suppression
    } catch (err) {
      console.error(err);
      alert("Échec de la suppression");
    }
  };

  const staticMemes = ["/meme1.png", "/meme2.png", "/meme3.png"];

  return (
    <div className="home">
      <h1>Bienvenue sur le Générateur de Mèmes 🎉</h1>

      <div className="links">
        <button onClick={() => (window.location.href = "/generate")}>
          Créer un mème
        </button>
        <button onClick={() => (window.location.href = "/favorites")}>
          Voir les favoris
        </button>
      </div>

      <h2>Mèmes disponibles :</h2>
      <div className="sample-memes">
        {staticMemes.map((url, idx) => (
          <img
            key={idx}
            src={url}
            alt={`Sample Meme ${idx}`}
            className="meme-preview"
            onClick={() => (window.location.href = `/generate${url}`)}
          />
        ))}
      </div>

      <h2>Galerie de Mèmes :</h2>
      <div className="gallery">
        {memes.map((meme) => (
          <div key={meme.id} className="meme-container">
            {meme.imagePaths.length > 0 ? (
              meme.imagePaths.map((path, i) => {
                const fileName = path.split("/").pop();
                return (
                  <img
                    key={i}
                    src={`http://localhost:8080/api/memes/image/${fileName}`}
                    alt={`Meme ${meme.id}`}
                    className="meme-preview"
                  />
                );
              })
            ) : (
              meme.base64Image && (
                <img
                  src={meme.base64Image}
                  alt={`Meme ${meme.id}`}
                  className="meme-preview"
                />
              )
            )}
            <button
              className="delete-button"
              onClick={() => handleDelete(meme.id)}
            >
              Supprimer
            </button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Home;
