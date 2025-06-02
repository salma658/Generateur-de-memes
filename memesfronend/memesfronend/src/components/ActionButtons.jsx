import React, { useState } from 'react';

const ActionButtons = ({ memeId, image, topText, bottomText }) => {
  const handleSave = async () => {
    if (!image) {
      alert("Aucune image générée.");
      return;
    }

    const payload = {
      base64Image: image,
      topText,
      bottomText,
    };

    try {
      const response = await fetch(`http://localhost:8080/api/memes/save`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(payload),
      });

      if (!response.ok) throw new Error("Échec de l'enregistrement");
      const result = await response.text();
      alert(result);
    } catch (error) {
      console.error(error);
      alert("Erreur lors de l'enregistrement");
    }
  };

  return (
    <div className="action-buttons">
      {/* <input type="file" multiple onChange={handleFileChange} /> */}
      <button onClick={handleSave}>Enregistrer</button>
      {/* <button onClick={handleEdit}>Modifier</button> */}
      {/* <button onClick={handleShare}>Partager</button> */}
    </div>
  );
};

export default ActionButtons;
