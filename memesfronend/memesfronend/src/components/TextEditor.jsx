import React from 'react';
import './TextEditor.css';

const TextEditor = ({ topText, bottomText, onTopTextChange, onBottomTextChange }) => {
  return (
    <div className="text-editor">
      <input
        type="text"
        placeholder="Texte du haut"
        value={topText}
        onChange={(e) => onTopTextChange(e.target.value)}
      />
      <input
        type="text"
        placeholder="Texte du bas"
        value={bottomText}
        onChange={(e) => onBottomTextChange(e.target.value)}
      />
    </div>
  );
};

export default TextEditor;
