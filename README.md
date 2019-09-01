# Warning!
If you'r thinking about choosing Vaadin for your frontend save other people from pain 
of using it and choose some different web framework...

This repo is only for for poor souls who have to sadly work with Vaadin and provides
some good practices and default components to make development more bearable

**Once again, please don't choose Vaadin for anything bigger than *FEW* views and without 
any *DESIGN* requirements, otherwise you your team and later on people who will have to manage
the inheritance and abstraction spaghetti are in for hell, thank you...**

# TODO
- [ ] (global) provide methods with self reference on all functional builders (e.g. TYPE with(Consumer<TYPE> withProvidedSelf)). Which will alow usage of builder current context in nested builders without creating instance of builder.
- [ ] (dialog) same handling of result in form and base dialog
- [ ] (view) generalize filtering (move base filter into own instance). Introduce specific builder for filter section, which will be hidable using standard toggle filter along with grid column filters
- [ ] (i18n/global) generalize translations by creating common translation object with configurable defaults, which just delegates to i18n instance with defaults provided as enums
- [ ] (view/module) (WIP - initial prototype is ready - DynamicViewLoader and using profiles to enable local one) Research how to split app into multiple modules (exported ui is not a problem, problem is if views have to be exported into different UI)
- [ ] (view) toggle filtering
- [ ] (dialog) replacable default accept/cancel handlers with defaults which use standard result api
- [ ] (dialog) adding data grid into dialog (scuffed UX but might be required...)
- [ ] (view) replacable instance of top/bottom in split view (introduction of self reference method should make it easy as both top and bottom methods should have root context during creation)
