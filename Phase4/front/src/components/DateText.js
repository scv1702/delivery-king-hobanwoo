import { memo } from 'react'

function DateText({ value }) {
  if (!value) return;
  return new Date(value).toLocaleDateString('ko-KR');
}

export default memo(DateText);
