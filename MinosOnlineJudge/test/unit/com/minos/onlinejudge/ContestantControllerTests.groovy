package com.minos.onlinejudge



import org.junit.*
import grails.test.mixin.*

@TestFor(ContestantController)
@Mock(Contestant)
class ContestantControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/contestant/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.contestantInstanceList.size() == 0
        assert model.contestantInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.contestantInstance != null
    }

    void testSave() {
        controller.save()

        assert model.contestantInstance != null
        assert view == '/contestant/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/contestant/show/1'
        assert controller.flash.message != null
        assert Contestant.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/contestant/list'

        populateValidParams(params)
        def contestant = new Contestant(params)

        assert contestant.save() != null

        params.id = contestant.id

        def model = controller.show()

        assert model.contestantInstance == contestant
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/contestant/list'

        populateValidParams(params)
        def contestant = new Contestant(params)

        assert contestant.save() != null

        params.id = contestant.id

        def model = controller.edit()

        assert model.contestantInstance == contestant
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/contestant/list'

        response.reset()

        populateValidParams(params)
        def contestant = new Contestant(params)

        assert contestant.save() != null

        // test invalid parameters in update
        params.id = contestant.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/contestant/edit"
        assert model.contestantInstance != null

        contestant.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/contestant/show/$contestant.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        contestant.clearErrors()

        populateValidParams(params)
        params.id = contestant.id
        params.version = -1
        controller.update()

        assert view == "/contestant/edit"
        assert model.contestantInstance != null
        assert model.contestantInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/contestant/list'

        response.reset()

        populateValidParams(params)
        def contestant = new Contestant(params)

        assert contestant.save() != null
        assert Contestant.count() == 1

        params.id = contestant.id

        controller.delete()

        assert Contestant.count() == 0
        assert Contestant.get(contestant.id) == null
        assert response.redirectedUrl == '/contestant/list'
    }
}
