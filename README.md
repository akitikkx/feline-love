# Feline Love
`Feline Love` is a Jetpack-powered, MVVM `Kotlin` Android application that allows the user to browse 
photos of cats retrieved from The Cat API. Upon loading the user can through the Jetpack Paging 3
library browse endlessly through a catalog of cat images and gifs. The user can view a fullscreen
version of the cat and share it's link with their friends.

### Pre-requisites
None

## Architecture
`Feline Love` is built using Kotlin and the following Jetpack components:

- ViewModel & LiveData 
- Navigation
- Paging 3
- SavedStateHandle with custom AbstractSavedStateViewModelFactory
- View Binding
- Data Binding

and the following additional libraries:

- Hilt
- Retrofit & OkHttp
- Glide
- Material Design

## Code and directory structure

```
> adapters
  |_ GlideBindingAdapter.kt
  |_ ResultBindingAdapter.kt
> di
  |_ RepositoryModule.kt
> domain
  |_ ImageSearchItem.kt
> network
  > models
    |_ NetworkImageSearchResponse.kt
    |_ NetworkImageSearchResponseItem.kt
  |_ CatApiService.kt
> repository
  |_ CatApiPagingSource.kt
  |_ CatApiRepository.kt
> ui
  > detail
    |_ DetailFragment.kt
    |_ DetailViewModel.kt
  > landing
    |_ CatResultsAdapter.kt
    |_ CatResultsLoadStateAdapter.kt
    |_ LandingFragment.kt
    |_ LandingViewModel.kt
|_ FelineLoveApplication.kt
|_ MainActivity.kt
```

#### adapters
All the binding adapters for the search and result screen/section as well as for loading images via Glide

#### di
All the related Dependency Injection classes such as modules for the Hilt component

#### domain
Contained here are data classes whose attributes match the preference of the application as compared to 
what is received from the network. 

#### network
The Cat API connection service

#### network > models
The data classes modelled against the responses from The Cat API

#### repository
All data to the app originates from the repository. For `Feline Love`, the repository
performs the network call through the connection service and emits the data which the viewModels listen for.
The repository makes use of a paging source to deliver pages on request.

#### ui
All the screens in the app separated into - landing and detail - and their respective fragments and viewModels

#### InsightApplication
`@HiltAndroidApp` annotated Application class

#### MainActivity
The entry point for the app and the container for the `Navigation`'s `NavHostFragment`

## Screenshots
<img src="https://github.com/akitikkx/feline-love/blob/main/screenshots/feline_love_screens.png">

## License

MIT License

Copyright (c) 2021 Ahmed Tikiwa

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
