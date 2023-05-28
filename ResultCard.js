import React from "react";
import UserCard from "./UserCard";
// import StoreList from "./StoreList";
import NearbyPlaces from "./MapInformation";

const ResultCard = ({ place, userCards }) => {
  // discount_rate가 가장 높은 카드 찾기
  const maxDiscountRate = Math.max(
    ...userCards.map((card) => card.discount_rate)
  );

  // accumulation_rate가 가장 높은 카드 찾기
  const maxAccumulationRate = Math.max(
    ...userCards.map((card) => card.accumulation_rate)
  );

  // 할인율, 적립률 각각 가장 높은 카드 찾기.
  const findBestCards = () => {
    let bestDiscountCard = null;
    let bestAccumulationCard = null;
    let maxDiscountRate = 0;
    let maxAccumulationRate = 0;

    userCards.forEach((card) => {
      // 모든 card에 대해
      card.benefits.forEach((benefit) => {
        // 각 card의 모든 benefit에 대해
        if (benefit.category === place.category_group_code) {
          // 가게의 카테고리와 혜택 카테고리가 일치하는 혜택
          if (benefit.discount_rate > maxDiscountRate) {
            // 최고할인율 찾기
            bestDiscountCard = card.name;
            maxDiscountRate = benefit.discount_rate;
          }

          if (benefit.accumulation_rate > maxAccumulationRate) {
            // 최고적립률 찾기
            bestAccumulationCard = card.name;
            maxAccumulationRate = benefit.accumulation_rate;
          }
        }
      });
    });

    return { bestDiscountCard, bestAccumulationCard };
  };

  const { bestDiscountCard, bestAccumulationCard } = findBestCards();

  return (
    <div>
      <h2>Best Cards for {place.place_name}</h2>
      {bestDiscountCard && (
        <div>
          <h3>Best Discount Card:</h3>
          <p>Card Name: {bestDiscountCard}</p>
          <p>Discount Rate: {maxDiscountRate}</p>
        </div>
      )}
      {bestAccumulationCard && (
        <div>
          <h3>Best Accumulation Card:</h3>
          <p>Card Name: {bestAccumulationCard}</p>
          <p>Accumulation Rate: {maxAccumulationRate}</p>
        </div>
      )}
      {!bestDiscountCard && !bestAccumulationCard && (
        <p>No matching cards found.</p>
      )}
    </div>
  );
};

export default ResultCard;

//     for (const card of cards) {
//       for (const benefit of card.benefits) {
//         if (benefit.category === "CS2") {
//           if (benefit.accumulation_rate > highestAccumulationRate && benefit.discount_rate > highestDiscountRate) {
//             bestCard = card;
//             highestAccumulationRate = benefit.accumulation_rate;
//             highestDiscountRate = benefit.discount_rate;
//           }
//         }
//       }
//     }

//     return bestCard;
//   };

//   const bestCard = findBestCard(userCards);
//   console.log("Best Card:", bestCard);
