import React from "react";
import ResultCard from "./ResultCard";

const UserCard = () => {
  const userCards = [
    {
      id: 1,
      user_id: 1,
      name: "현대카드M Edition3",
      company: "현대카드",
      benefits: [
        { category: "FD6", discount_rate: 0.1, accumulation_rate: 0.02 },
        { category: "CE7", discount_rate: 0.05, accumulation_rate: 0.03 },
        { category: "CS2", discount_rate: 0.03, accumulation_rate: 0.01 },
      ],
    },
    {
      id: 2,
      user_id: 1,
      name: "카카오 프렌즈 체크카드",
      company: "카카오뱅크",
      benefits: [
        { category: "FD6", discount_rate: 0.05, accumulation_rate: 0.01 },
        { category: "CE7", discount_rate: 0.03, accumulation_rate: 0.02 },
        { category: "CS2", discount_rate: 0.02, accumulation_rate: 0.03 },
      ],
    },
    {
      id: 3,
      user_id: 1,
      name: "첵첵 체크카드",
      company: "KB국민은행",
      benefits: [
        { category: "FD6", discount_rate: 0.03, accumulation_rate: 0.03 },
        { category: "CE7", discount_rate: 0.02, accumulation_rate: 0.02 },
        { category: "CS2", discount_rate: 0.01, accumulation_rate: 0.01 },
      ],
    },
  ];

  const findUserCards = (cards, session_user_id) => {
    return cards.filter((card) => card.user_id === session_user_id);
  };

  const session_user_id = 1; // 로그인 유저의 user_id가 1이라고 가정
  const user_cards_of_user = findUserCards(userCards, session_user_id); // user_id = 1 유저가 보유한 카드 목록 불러오기

  // ResultCard.js 컴포넌트로 user_cards_of_user를 props로 전달
  return <ResultCard userCards={user_cards_of_user} />;
};

export default UserCard;

// ver1
// const UserCard = () => {
//   const userCards = [
//     {
//       id: 1,
//       name: "현대카드M Edition3",
//       company: "Hyundai card",
//       discount_rate: "0.05",
//       accrual_rate: "0.00",
//       category_group_code: "CS2",
//     },
//     {
//       id: 2,
//       name: "카카오 프렌즈 체크카드",
//       company: "Kakaobank",
//       discount_rate: "0.00",
//       accrual_rate: "0.02",
//       category_group_code: "CS2",
//     },
//     {
//       id: 3,
//       name: "첵첵 체크카드",
//       company: "KB bank",
//       discount_rate: "0.01",
//       accrual_rate: "0.01",
//     },
//     {
//       id: 4,
//       name: "Card C",
//       company: "Company C",
//       discount_rate: "0.02",
//       accrual_rate: "0.02",
//     },
//   ];

//   const selectedCardId = 1; // 선택된 카드의 id

//   // 선택된 카드를 찾아서 해당 카드의 혜택 정보를 가져오는 함수
//   const getCardBenefit = (cardId) => {
//     // 카드별 하드코딩된 혜택 정보
//     const cardBenefits = {
//       1: "Card A의 혜택 정보",
//       2: "Card B의 혜택 정보",
//       3: "Card C의 혜택 정보",
//     };

//     return cardBenefits[cardId] || "해당 카드의 혜택 정보가 없습니다.";
//   };

//   const selectedCard = userCards.find((card) => card.id === selectedCardId);

//   return (
//     <div>
//       <h2>유저의 카드 목록</h2>
//       <ul>
//         {userCards.map((card) => (
//           <li key={card.id}>
//             {card.name} ({card.company})
//           </li>
//         ))}
//       </ul>
//       <h3>선택된 카드: {selectedCard.name}</h3>
//       <CardBenefit benefit={getCardBenefit(selectedCardId)} />
//     </div>
//   );
// };

// export default UserCard;

/* import React from 'react';

const UserCard = () => {
  const userCards = [
    { id: 1, name: 'Card A', company: 'Company A', discount_rate: '0.05', accrual_rate: '0.00' },
    { id: 2, name: 'Card B', company: 'Company B', discount_rate: '0.00', accrual_rate: '0.30' },
    { id: 3, name: 'Card C', company: 'Company C', discount_rate: '0.02', accrual_rate: '0.02' },
  ];

  const matchedCard = userCards.find((card) => card.id === 2); // 특정 카드 id에 해당하는 카드

  return (
    <div>
      <h2>유저 카드 정보</h2>
      <p>유저 ID: 1</p>
      <p>유저 이름: John Doe</p>
      <p>카드 ID: {matchedCard.id}</p>
      <p>카드 이름: {matchedCard.name}</p>
      <p>카드 회사: {matchedCard.company}</p>
      <p>카드 적립율: {matchedCard.accrual_rate}</p>
      <p>카드 할인율: {matchedCard.discount_rate}</p>
    </div>
  );
};

export default UserCard; */

// [카테고리] FD6: 음식점, CE7: 카페, CS2: 편의점

// const userCards = [
//   {
//     id: 1,
//     user_id: 1,
//     name: "현대카드M Edition3",
//     company: "현대카드",
//     benefits: [
//       { category: "FD6", discount_rate: 0.1, accumulation_rate: 0.02 },
//       { category: "CE7", discount_rate: 0.05, accumulation_rate: 0.03 },
//       { category: "CS2", discount_rate: 0.03, accumulation_rate: 0.01 },
//     ],
//   },
//   {
//     id: 2,
//     user_id: 1,
//     name: "카카오 프렌즈 체크카드",
//     company: "카카오뱅크",
//     benefits: [
//       { category: "FD6", discount_rate: 0.05, accumulation_rate: 0.01 },
//       { category: "CE7", discount_rate: 0.03, accumulation_rate: 0.02 },
//       { category: "CS2", discount_rate: 0.02, accumulation_rate: 0.03 },
//     ],
//   },
//   {
//     id: 3,
//     user_id: 1,
//     name: "첵첵 체크카드",
//     company: "KB국민은행",
//     benefits: [
//       { category: "FD6", discount_rate: 0.03, accumulation_rate: 0.03 },
//       { category: "CE7", discount_rate: 0.02, accumulation_rate: 0.02 },
//       { category: "CS2", discount_rate: 0.01, accumulation_rate: 0.01 },
//     ],
//   },
// ];

// const findUserCards = (cards, session_user_id) => {
//   return cards.filter((card) => card.user_id === session_user_id);
// };

// const session_user_id = 1; // 로그인 유저의 user_id가 1이라고 가정
// const user_cards_of_user = findUserCards(userCards, session_user_id); // user_id = 1 유저가 보유한 카드 목록 불러오기

// // ResultCard.js 컴포넌트로 userCardsOfUser를 props로 전달
// return <ResultCard userCards={user_cards_of_user} />;

// const selectedCard = userCards.find(
//   (card) => card.name === "현대카드M Edition3"
// );

// const selectedBenefit = selectedCard.benefits.find(
//   (benefit) => benefit.category === "CS2"
// ); // 편의점

// const discountRate = selectedBenefit.discount_rate;
// const accrualRate = selectedBenefit.accumulation_rate;
