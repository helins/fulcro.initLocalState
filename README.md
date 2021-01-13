# Fulcro - :initLocalState misbehaving

Related to this [issue](https://github.com/fulcrologic/fulcro/issues/451).

For some reason, `:initLocalState` does not hot reload in any way although
recompilation is successful and any other change to a component does apply as
expected.

## Usage

Launching dev build:

```shell
$ ./bin/dev
```

When everything compiles, open the app and start adding children by clicking on
the button. On `:initLocalState`, a message is printed. Try modifying this
message in the `Child` component in `./src/main/app/root.cljs` and notice how nothing changes
when new children are added after recompilation.

## License

Copyright © 2021 Adam Helinski

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
