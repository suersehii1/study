// src/data/menu.js
export const menuData = [
  {
    id: 1,
    name: "Home",
    path: "/main/home", // Home 화면 경로
  },
  {
    id: 2,
    name: "Grid",
    children: [
      {
        id: 3,
        name: "AgGrid",
        children: [
          { id: 4, name: "selectGrid", path: "/grid/selectGrid" },
          { id: 5, name: "addGridData", path: "/grid/addGridData" },
        ],
      },
      {
        id: 6,
        name: "AUIGrid",
        children: [
          { id: 7, name: "Page 3", path: "/main/page3" },
        ],
      },
    ],
  },
  {
    id: 8,
    name: "DB",
    children: [
      {
        id: 9,
        name: "DB1",
        children: [
          { id: 10, name: "DB2", path: "/main/page4" },
        ],
      },
    ],
  },
];
