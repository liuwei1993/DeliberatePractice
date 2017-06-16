package com.simon.algorithm.string;

public class StringWrap {
        final String string;
        int start;
        int length;

        public StringWrap(String string, int start, int length) {
            this.string = string;
            this.start = start;
            this.length = length;
            checkBounds(string, start, length);
        }

        private void checkBounds(String string, int start, int length) {
            if(length == 0) return;
            int strLength = string.length();
            if (start > strLength - 1 || start + length > strLength) {
                throw new IllegalArgumentException("out of bounds start " + start + " length " + length + " str length " + strLength);
            }
        }

        public StringWrap(StringWrap stringWrap, int start, int length) {
            this.string = stringWrap.string;
            this.start = stringWrap.start + start;
            this.length = length;
            checkBounds(string, this.start, length);
        }

        String charAt(int index) {
            return String.valueOf(string.charAt(index + start));
        }

        public int indexOf(String s) {
            if (s != null) {
                for (int i = 0; i < length; i++) {
                    String charAt = charAt(i);
                    if (s.equals(charAt)) {
                        return i;
                    }
                }
            }
            return -1;
        }

        public String firstChar() {
            return charAt(0);
        }

        public String endChar() {
            return charAt(length - 1);
        }

        public int length() {
            return length;
        }

    }
