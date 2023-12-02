import {
  banglaContentDictionary,
  englishContentDictionary
} from "./lingualDictionary";
import * as types from "./types";

const initialState = {
  isEnglish: true,
  isBangla: false,
  dictionary: englishContentDictionary,
};

const lingualReducer = (state = initialState, action) => {
  switch (action.type) {
    case types.TOGGLE_LINGUAL: {
      return {
        isEnglish: state.isEnglish ? false : true,
        isBangla: state.isBangla ? false : true,
        dictionary: state.isEnglish
          ? banglaContentDictionary
          : englishContentDictionary,
      };
    }

    case types.CHANGE_LINGUAL_TO_BANGLA: {
      return {
        isEnglish: false,
        isBangla: true,
        dictionary: banglaContentDictionary,
      };
    }
    case types.CHANGE_LINGUAL_TO_ENGISH: {
      return {
        isEnglish: true,
        isBangla: false,
        dictionary: englishContentDictionary,
      };
    }

    default:
      return state;
  }
};

export default lingualReducer;
