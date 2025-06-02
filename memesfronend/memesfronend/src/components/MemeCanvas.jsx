// MemeCanvas.jsx
import React, { useEffect } from 'react';

const MemeCanvas = React.forwardRef(({ image, setImage, topText, bottomText }, ref) => {
  useEffect(() => {
    const canvas = ref.current;
    if (!canvas) return;

    const ctx = canvas.getContext('2d');
    const img = new Image();
    img.src = image;

    img.onload = () => {
      ctx.clearRect(0, 0, canvas.width, canvas.height);
      ctx.drawImage(img, 0, 0, canvas.width, canvas.height);
      ctx.font = '30px Impact';
      ctx.fillStyle = 'white';
      ctx.strokeStyle = 'black';
      ctx.textAlign = 'center';
      ctx.lineWidth = 2;
      ctx.fillText(topText, canvas.width / 2, 40);
      ctx.strokeText(topText, canvas.width / 2, 40);
      ctx.fillText(bottomText, canvas.width / 2, canvas.height - 20);
      ctx.strokeText(bottomText, canvas.width / 2, canvas.height - 20);
      // 🔥 Set image to drawn canvas as data URL
      if (setImage) {
        const dataUrl = canvas.toDataURL("image/png");
        setImage(dataUrl);
      }
    };
  }, [image, topText, bottomText, ref, setImage]);

  return <canvas ref={ref} width="500" height="500" />;
});

export default MemeCanvas;
