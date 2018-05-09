package com.pcun.b1.a1b_pcun_ma;

import com.apollographql.apollo.api.InputFieldMarshaller;
import com.apollographql.apollo.api.InputFieldWriter;
import com.apollographql.apollo.api.Mutation;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.OperationName;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.api.ResponseFieldMarshaller;
import com.apollographql.apollo.api.ResponseReader;
import com.apollographql.apollo.api.ResponseWriter;
import com.apollographql.apollo.api.internal.UnmodifiableMapBuilder;
import com.apollographql.apollo.api.internal.Utils;
import java.io.IOException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import javax.annotation.Nonnull;

@Generated("Apollo GraphQL")
public final class LoginMutation implements Mutation<LoginMutation.Data, LoginMutation.Data, LoginMutation.Variables> {
    public static final String OPERATION_DEFINITION = "mutation LoginMutation($email: String!, $password: String!) {\n"
            + "  login(email: $email, password: $password) {\n"
            + "    __typename\n"
            + "    userid\n"

            + "    username\n"

            + "  }\n"
            + "}";

    public static final String OPERATION_ID = "ca222d64b9c7e7247b1a008de15c149b5458d299ade44a017df62e00db609ca2";

    public static final String QUERY_DOCUMENT = OPERATION_DEFINITION;

    public static final OperationName OPERATION_NAME = new OperationName() {
        @Override
        public String name() {
            return "LoginMutation";
        }
    };

    private final Variables variables;

    public LoginMutation(@Nonnull String email, @Nonnull String password) {
        Utils.checkNotNull(email, "email == null");
        Utils.checkNotNull(password, "password == null");
        variables = new Variables(email, password);
    }

    @Override
    public String operationId() {
        return OPERATION_ID;
    }

    @Override
    public String queryDocument() {
        return QUERY_DOCUMENT;
    }

    @Override
    public Data wrapData(Data data) {
        return data;
    }

    @Override
    public Variables variables() {
        return variables;
    }

    @Override
    public ResponseFieldMapper<Data> responseFieldMapper() {
        return new Data.Mapper();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public OperationName name() {
        return OPERATION_NAME;
    }

    public static final class Builder {
        private @Nonnull String email;

        private @Nonnull String password;

        Builder() {
        }

        public Builder email(@Nonnull String email) {
            this.email = email;
            return this;
        }

        public Builder password(@Nonnull String password) {
            this.password = password;
            return this;
        }

        public LoginMutation build() {
            Utils.checkNotNull(email, "email == null");
            Utils.checkNotNull(password, "password == null");
            return new LoginMutation(email, password);
        }
    }

    public static final class Variables extends Operation.Variables {
        private final @Nonnull String email;

        private final @Nonnull String password;

        private final transient Map<String, Object> valueMap = new LinkedHashMap<>();

        Variables(@Nonnull String email, @Nonnull String password) {
            this.email = email;
            this.password = password;
            this.valueMap.put("email", email);
            this.valueMap.put("password", password);
        }

        public @Nonnull String email() {
            return email;
        }

        public @Nonnull String password() {
            return password;
        }

        @Override
        public Map<String, Object> valueMap() {
            return Collections.unmodifiableMap(valueMap);
        }

        @Override
        public InputFieldMarshaller marshaller() {
            return new InputFieldMarshaller() {
                @Override
                public void marshal(InputFieldWriter writer) throws IOException {
                    writer.writeString("email", email);
                    writer.writeString("password", password);
                }
            };
        }
    }

    public static class Data implements Operation.Data {
        static final ResponseField[] $responseFields = {
                ResponseField.forObject("login", "login", new UnmodifiableMapBuilder<String, Object>(2)
                        .put("password", new UnmodifiableMapBuilder<String, Object>(2)
                                .put("kind", "Variable")
                                .put("variableName", "password")
                                .build())
                        .put("username", new UnmodifiableMapBuilder<String, Object>(2)
                                .put("kind", "Variable")
                                .put("variableName", "email")
                                .build())
                        .build(), false, Collections.<ResponseField.Condition>emptyList())
        };

        final @Nonnull Login login;

        private volatile String $toString;

        private volatile int $hashCode;

        private volatile boolean $hashCodeMemoized;

        public Data(@Nonnull Login login) {
            this.login = Utils.checkNotNull(login, "login == null");
        }

        public @Nonnull Login login() {
            return this.login;
        }

        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() {
                @Override
                public void marshal(ResponseWriter writer) {
                    writer.writeObject($responseFields[0], login.marshaller());
                }
            };
        }

        @Override
        public String toString() {
            if ($toString == null) {
                $toString = "Data{"
                        + "login=" + login
                        + "}";
            }
            return $toString;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof Data) {
                Data that = (Data) o;
                return this.login.equals(that.login);
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (!$hashCodeMemoized) {
                int h = 1;
                h *= 1000003;
                h ^= login.hashCode();
                $hashCode = h;
                $hashCodeMemoized = true;
            }
            return $hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Data> {
            final Login.Mapper loginFieldMapper = new Login.Mapper();

            @Override
            public Data map(ResponseReader reader) {
                final Login login = reader.readObject($responseFields[0], new ResponseReader.ObjectReader<Login>() {
                    @Override
                    public Login read(ResponseReader reader) {
                        return loginFieldMapper.map(reader);
                    }
                });
                return new Data(login);
            }
        }
    }

    public static class Login {
        static final ResponseField[] $responseFields = {
                ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
                ResponseField.forLong("userid", "userid", null, false, Collections.<ResponseField.Condition>emptyList()),

                ResponseField.forString("username", "username", null, false, Collections.<ResponseField.Condition>emptyList()),

        };

        final @Nonnull String __typename;

        final long userid;

        final @Nonnull String username;

        private volatile String $toString;

        private volatile int $hashCode;

        private volatile boolean $hashCodeMemoized;

        public Login(@Nonnull String __typename, long userid, @Nonnull String username) {
            this.__typename = Utils.checkNotNull(__typename, "__typename == null");
            this.userid = userid;

            this.username = Utils.checkNotNull(username, "username == null");

        }

        public @Nonnull String __typename() {
            return this.__typename;
        }

        public long userid() {
            return this.userid;
        }



        public @Nonnull String username() {
            return this.username;
        }



        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() {
                @Override
                public void marshal(ResponseWriter writer) {
                    writer.writeString($responseFields[0], __typename);
                    writer.writeLong($responseFields[1], userid);
                    writer.writeString($responseFields[2], username);


                }
            };
        }

        @Override
        public String toString() {
            if ($toString == null) {
                $toString = "Login{"
                        + "__typename=" + __typename + ", "
                        + "userid=" + userid + ", "

                        + "username=" + username + ", "

                        + "}";
            }
            return $toString;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof Login) {
                Login that = (Login) o;
                return this.__typename.equals(that.__typename)
                        && this.userid == that.userid

                        && this.username.equals(that.username)

            }
            return false;
        }

        @Override
        public int hashCode() {
            if (!$hashCodeMemoized) {
                int h = 1;
                h *= 1000003;
                h ^= __typename.hashCode();
                h *= 1000003;
                h ^= userid;
                h *= 1000003;

                h *= 1000003;
                h ^= username.hashCode();
                h *= 1000003;

                $hashCode = h;
                $hashCodeMemoized = true;
            }
            return $hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Login> {
            @Override
            public Login map(ResponseReader reader) {
                final String __typename = reader.readString($responseFields[0]);
                final long userid = reader.readLong($responseFields[1]);
                final String username = reader.readString($responseFields[2]);

                return new Login(__typename, userid, username);
            }
        }
    }
}
