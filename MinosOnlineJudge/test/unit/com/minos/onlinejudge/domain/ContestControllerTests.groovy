package com.minos.onlinejudge.domain



import org.junit.*
import grails.test.mixin.*

@TestFor(ContestController)
@Mock(Contest)
class ContestControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/contest/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.contestInstanceList.size() == 0
        assert model.contestInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.contestInstance != null
    }

    void testSave() {
        controller.save()

        assert model.contestInstance != null
        assert view == '/contest/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/contest/show/1'
        assert controller.flash.message != null
        assert Contest.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/contest/list'

        populateValidParams(params)
        def contest = new Contest(params)

        assert contest.save() != null

        params.id = contest.id

        def model = controller.show()

        assert model.contestInstance == contest
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/contest/list'

        populateValidParams(params)
        def contest = new Contest(params)

        assert contest.save() != null

        params.id = contest.id

        def model = controller.edit()

        assert model.contestInstance == contest
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/contest/list'

        response.reset()

        populateValidParams(params)
        def contest = new Contest(params)

        assert contest.save() != null

        // test invalid parameters in update
        params.id = contest.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/contest/edit"
        assert model.contestInstance != null

        contest.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/contest/show/$contest.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        contest.clearErrors()

        populateValidParams(params)
        params.id = contest.id
        params.version = -1
        controller.update()

        assert view == "/contest/edit"
        assert model.contestInstance != null
        assert model.contestInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/contest/list'

        response.reset()

        populateValidParams(params)
        def contest = new Contest(params)

        assert contest.save() != null
        assert Contest.count() == 1

        params.id = contest.id

        controller.delete()

        assert Contest.count() == 0
        assert Contest.get(contest.id) == null
        assert response.redirectedUrl == '/contest/list'
    }
}
