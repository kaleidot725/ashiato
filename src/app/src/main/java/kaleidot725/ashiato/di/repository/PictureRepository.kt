package kaleidot725.ashiato.di.repository

import kaleidot725.ashiato.di.service.picture.Picture

interface PictureRepository {
    fun all(): List<Picture>
    fun count(): Int

    fun tmpPicture(): Picture
    fun newPicture(): Picture

    fun take(picture: Picture)
    val took: Picture?

    fun action(picture: Picture)
    val actioned: Picture?

    fun preview(picture: Picture)
    val previewed: Picture?
}