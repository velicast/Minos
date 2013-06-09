package com.minos.onlinejudge



import org.junit.*
import grails.test.mixin.*

@TestFor(ProblemController)
@Mock(Problem)
class ProblemControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/problem/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.problemInstanceList.size() == 0
        assert model.problemInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.problemInstance != null
    }

    void testSave() {
        controller.save()

        assert model.problemInstance != null
        assert view == '/problem/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/problem/show/1'
        assert controller.flash.message != null
        assert Problem.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/problem/list'

        populateValidParams(params)
        def problem = new Problem(params)

        assert problem.save() != null

        params.id = problem.id

        def model = controller.show()

        assert model.problemInstance == problem
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/problem/list'

        populateValidParams(params)
        def problem = new Problem(params)

        assert problem.save() != null

        params.id = problem.id

        def model = controller.edit()

        assert model.problemInstance == problem
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/problem/list'

        response.reset()

        populateValidParams(params)
        def problem = new Problem(params)

        assert problem.save() != null

        // test invalid parameters in update
        params.id = problem.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/problem/edit"
        assert model.problemInstance != null

        problem.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/problem/show/$problem.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        problem.clearErrors()

        populateValidParams(params)
        params.id = problem.id
        params.version = -1
        controller.update()

        assert view == "/problem/edit"
        assert model.problemInstance != null
        assert model.problemInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/problem/list'

        response.reset()

        populateValidParams(params)
        def problem = new Problem(params)

        assert problem.save() != null
        assert Problem.count() == 1

        params.id = problem.id

        controller.delete()

        assert Problem.count() == 0
        assert Problem.get(problem.id) == null
        assert response.redirectedUrl == '/problem/list'
    }
}
