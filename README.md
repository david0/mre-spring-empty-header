Example for [Spring Issue #33164](https://github.com/spring-projects/spring-framework/issues/33164).

Client Error is: `IllegalArgumentException: empty headers are not allowed []` in this case.

The server returns an empty header sometimes (`:`).
