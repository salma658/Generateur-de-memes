import React, { useState, useRef, useEffect } from "react"; // Hooks React
import ImageUploader from "./ImageUploader"; // Assure-toi que le chemin est correct
import TextEditor from "./TextEditor";
import MemeCanvas from "./MemeCanvas";
import ActionButtons from "./ActionButtons";
import { useParams } from 'react-router-dom';

const MemeGenerator = () => {
  const { memeUrl } = useParams();
  const [image, setImage] = useState(memeUrl ? `/${memeUrl}` : null);
  const [topText, setTopText] = useState("");
  const [bottomText, setBottomText] = useState("");
  const [memeId, setMemeId] = useState("123"); // exemple statique pour tester
  const canvasRef = useRef(null);
  useEffect(() => {
    if (memeUrl) {
      setImage(`${memeUrl}`);
    }
  }, [memeUrl]);

  console.log('memeUrl', memeUrl);
  
  return (
    <div className="generator">
      <h1>Générateur de Mèmes</h1>
      <ImageUploader onImageUpload={setImage} />
      <TextEditor
        topText={topText}
        bottomText={bottomText}
        onTopTextChange={setTopText}
        onBottomTextChange={setBottomText}
      />
      <MemeCanvas
        image={image}
        setImage={setImage}
        topText={topText}
        bottomText={bottomText}
        ref={canvasRef}
      />
      {/* Ici on passe memeId à ActionButtons */}
      <ActionButtons
        memeRef={canvasRef}
        memeId={memeId}
        image={image}
        topText={topText}
        bottomText={bottomText}
      />
    </div>
  );
};
export default MemeGenerator;
