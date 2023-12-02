export const numberWithCommas = (x) => {
  if (x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
  } else return x;
};

export const authHeader = () => {
  const authToken = localStorage.getItem("token");

  if (authToken) {
    return {
      "Content-Type": "application/json",
      Authorization: "Bearer "+authToken,
    };
  }

  return {};
};

export const updateObject = (oldObject, updatedProperties) => {
  return {
    ...oldObject,
    ...updatedProperties,
  };
};

export const checkValidity = (value, rules) => {
  let isValid = true;
  if (!rules) {
    return true;
  }

  if (rules.required) {
    isValid = value.trim() !== "" && isValid;
  }

  if (rules.minLength) {
    isValid = value.length >= rules.minLength && isValid;
  }

  if (rules.maxLength) {
    isValid = value.length <= rules.maxLength && isValid;
  }

  if (rules.isEmail) {
    const pattern =
      /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/;
    isValid = pattern.test(value) && isValid;
  }

  if (rules.isNumeric) {
    const pattern = /^\d+$/;
    isValid = pattern.test(value) && isValid;
  }

  return isValid;
};


const numbers =  {
  '0': '০',
   '1': '১',
   '২': '২',
   '3': '৩',
   '4': '৪',
   '5': '৫',
   '6': '৬',
   '7': '৭',
   '8': '৮',
   '9': '৯',
 }
 export const replaceNumbers = (input) =>  {
   if(!input){
    // return 0; 
    return ;
   }
   var output = [];
   for (var i = 0; i < input.length; ++i) {
     if (numbers.hasOwnProperty(input[i])) {
       output.push(numbers[input[i]]);
     } else {
       output.push(input[i]);
     }
   }
   return output.join('');
 }
