<template>
  <ag-grid-vue
      class="ag-theme-alpine"
      style="width: 100%; height: 100%;"
      :rowData="gridData"
      :columnDefs="gridColumns"
      :gridOptions="gridOptions"
      @grid-ready="onGridReady"
  ></ag-grid-vue>
</template>

<script>

import { AgGridVue } from "ag-grid-vue3";
import "ag-grid-community/styles/ag-grid.css";// Ag-Grid 스타일
import "ag-grid-community/styles/ag-theme-alpine.css";// Ag-Grid 테마 스타일
import { ModuleRegistry } from 'ag-grid-community';
import { ClientSideRowModelModule } from 'ag-grid-community';

ModuleRegistry.registerModules([ ClientSideRowModelModule ]);



export default {
  name: "CommonAgGrid",
  components: {
    AgGridVue,
  },
  props: {
    gridData: {
      type: Array,
      required: true,
    },
    gridColumns: {
      type: Array,
      required: true,
    },
    gridOptions: {
      type: Object,
      default: () => ({}),
    },
  },
  created() {
    console.log("created")
  },
  mounted() {
    console.log("mounted")
  },
  methods: {
    onGridReady(params) {
      debugger
      this.gridApi = params.api;
      this.gridColumnApi = params.columnApi;
    },
    getGridApi() {
      return this.gridApi;
    },
    getGridColumnApi() {
      return this.gridColumnApi;
    },
  },
};
</script>

<style>
/* 테마 스타일을 지정합니다 */
.ag-theme-alpine {
  height: 100%;
  width: 100%;
}
</style>
