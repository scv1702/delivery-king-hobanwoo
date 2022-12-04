import { DBError } from "oracledb";

export const getCurrentKorDate = () => {
  const curr = new Date();
  const utc = curr.getTime() + curr.getTimezoneOffset() * 60 * 1000;
  const KR_TIME_DIFF = 9 * 60 * 60 * 1000;
  const kr_curr = new Date(utc + KR_TIME_DIFF);
  return kr_curr;
};

export const getAllResolvedResult = <T>(promises: Promise<T>[] | undefined) => {
  if (promises) {
    const result = Promise.all(promises);
    return result;
  } else {
    return undefined;
  }
};

export function isDBError(err: any): err is DBError {
  return "errorNum" in err && "message" in err && "offset" in err;
}
