import React from 'react';
import './StarRating.css';

const StarRating = ({ rating }) => {
  // Điểm số của bạn (ví dụ: 4.5)
  const score = Math.floor(rating * 2) / 2; // Làm tròn điểm số đến 0.5
  
  // Tạo danh sách các biểu tượng sao
  const stars = [];
  for (let i = 0; i < 5; i++) {
    if (i < score) {
      // Sao đầy (Full star) cho điểm cao hơn hoặc bằng
      stars.push(<span className="star" key={i}>&#9733;</span>);
    } else if (i === Math.floor(score) && score % 1 !== 0) {
      // Sao bán (Half star) cho điểm số có phần thập phân (0.5, 1.5, 2.5, ...)
      stars.push(<span className="star" key={i}>&#9734;&#9733;</span>);
    } else {
      // Sao trống (Empty star) cho điểm thấp hơn
      stars.push(<span className="star" key={i}>&#9734;</span>);
    }
  }

  return (
    <div className="star-rating">
      {stars.map((star, index) => (
        <span key={index}>{star}</span>
      ))}
    </div>
  );
};

export default StarRating;
