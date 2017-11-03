package clertonleal.com.githubloader.viewModel


import clertonleal.com.githubloader.model.Repository

class RepositoryViewModel(var repository: Repository) {

    fun starsCount(): String {
        return repository.stargazersCount.toString()
    }

    fun forksCount(): String {
        return repository.forksCount.toString()
    }
}
