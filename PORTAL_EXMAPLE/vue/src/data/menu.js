// src/data/menu.js
export const menuData = [
  {
    id: 1,
    name: "Home",
    path: "/main/home", // Home 화면 경로
  },
  {
    id: 2,
    name: "Grid사용법",
    children: [
      {
        id: 3,
        name: "AgGrid사용",
        children: [
          { id: 4, name: "조회(일반)", path: "/grid/selectGrid" },
          { id: 5, name: "조회(콤보)", path: "/grid/selectGridCombo" },
        ],
      },
      {
        id: 6,
        name: "AUIGrid사용",
        children: [
          { id: 7, name: "Page 3", path: "/main/page3" },
        ],
      },
    ],
  },
  {
    id: 8,
    name: "Admin",
    children: [
      {
        id: 9,
        name: "User관리",
        children: [
          { id: 10, name: "User수정", path: "/admin/userModify" },
        ],
      },
    ],
  },
];
