import React from 'react';
import './ImageUploader.css';

const ImageUploader = ({ onImageUpload }) => {
  const handleImageChange = (e) => {
    const file = e.target.files[0];
    if (!file) return;
    const reader = new FileReader();
    reader.onloadend = () => {
      onImageUpload(reader.result);
    };
    reader.readAsDataURL(file);
  };

  return (
    <div className="image-uploader">
      <label className="upload-label">
        Télécharger une image
        <input type="file" name='' accept="image/*" onChange={handleImageChange} hidden />
      </label>
    </div>
  );
};

export default ImageUploader;
